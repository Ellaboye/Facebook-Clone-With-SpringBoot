package com.example.week7.controller;

import com.example.week7.model.Users;
import com.example.week7.serviceImplementation.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LikeController {

   private LikeServiceImpl likesService;

    @Autowired
    public LikeController(LikeServiceImpl likesService) {
        this.likesService = likesService;
    }

    /**
     * Post request to process like made by users
     * redirects user if not in session
     * saves or deletes like in or from the database, or perhaps fails
     * redirect back to home page
     * */
    @RequestMapping(value = "/processLike", method = RequestMethod.POST)
    public @ResponseBody
    String likePost(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        Users person = (Users) session.getAttribute("user");

        if(person == null) return "redirect:/";

        Long postId = Long.parseLong(request.getParameter("postId"));
        String action = request.getParameter("action");

        if(likesService.likePost(person, postId, action)){
            return "successful";
        }else{
            session.setAttribute("message", "server error");
        }

        return "";
    }
}
