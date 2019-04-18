package com.liyv.ssm.service.impl;

import com.liyv.ssm.dao.AppointmentDao;
import com.liyv.ssm.dao.BookDao;
import com.liyv.ssm.dto.AppointExecution;
import com.liyv.ssm.entity.Appointment;
import com.liyv.ssm.entity.Book;
import com.liyv.ssm.enums.AppointStateEnum;
import com.liyv.ssm.exception.AppointException;
import com.liyv.ssm.exception.NoNumberException;
import com.liyv.ssm.exception.RepeatAppointException;
import com.liyv.ssm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AppointmentDao appointmentDao;

    public Book getById(long bookId) {
        return bookDao.queryById(bookId);
    }

    public List<Book> getList() {
        return bookDao.queryAll(0, 1000);
    }

    @Transactional
    public AppointExecution appoint(long bookId, long studentId) {
        try {
            //减库存
            int update = bookDao.reduceNumber(bookId);
            if (update <= 0) {
                throw new NoNumberException("no number");
            }else {
                //执行预约操作
                int insert = appointmentDao.insertAppointment(bookId, studentId);
                if (insert <= 0) {
                    throw new RepeatAppointException("repeat appoint");
                }else {
                    Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
                    return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
                }
            }
        }catch (NoNumberException e1){
            throw e1;
        }catch (RepeatAppointException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有编译器异常转换为运行器异常，
            //这是什么意思
            throw new AppointException("appoint inner error:" + e.getMessage());
        }
    }
}
