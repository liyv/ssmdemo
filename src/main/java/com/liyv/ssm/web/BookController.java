package com.liyv.ssm.web;

import com.liyv.ssm.dto.AppointExecution;
import com.liyv.ssm.dto.Result;
import com.liyv.ssm.entity.Book;
import com.liyv.ssm.enums.AppointStateEnum;
import com.liyv.ssm.exception.NoNumberException;
import com.liyv.ssm.exception.RepeatAppointException;
import com.liyv.ssm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    private Object list(Model model) {
        List<Book> list = bookService.getList();
        model.addAttribute("list", list);
        //list.jsp + model=ModelAndView
        return list;// WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
    private String detail(@PathVariable("bookId") Long bookId, Model model) {
        if (null == bookId) {
            return "redirect:/book/list";
        }

        Book book = bookService.getById(bookId);
        if (null == book) {
//            return "forward:/book/list";
            return "redirect:/appoint/list2";
        }
        model.addAttribute("book", book);
        return "detail";
    }

    @RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {
            "application/json;charset=utf-8"})
    @ResponseBody
    private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @RequestParam("studentId") Long studentId) {
        if (null == studentId || studentId.equals("")) {
            return new Result<AppointExecution>(false, "学号不能为空");
        }
        AppointExecution execution = null;
        try {
            execution = bookService.appoint(bookId, studentId);
        } catch (NoNumberException e1) {
            execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
        } catch (RepeatAppointException e2) {
            execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
        } catch (Exception e) {
            execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
        }
        return new Result<AppointExecution>(true, execution);
    }

}
