package com.liyv.ssm.web;

import com.liyv.ssm.entity.Book;
import com.liyv.ssm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/appoint")
public class AppointmentController {

    private Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    @ResponseBody
    private Object list2() {
        List<Book> list2 = bookService.getList();
        return list2;
    }
}
