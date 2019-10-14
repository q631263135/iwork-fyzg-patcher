package com.joyin.readinglist.controller;

import com.joyin.readinglist.entity.Book;
import com.joyin.readinglist.repository.ReadingListRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping
@Log4j2
public class ReadingListController {
    // 大萨达
    private ReadingListRepository readingListRepository;


    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }

        log.info("readersBooks");
        System.out.println(111);
        return "readingList";
    }

    /**
     *
     * @param reader
     * @param book
     * @return
     */
    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadlingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);

        return "redirect:/{reader}";
    }
}
