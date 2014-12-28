package com.springapp.mvc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPerson() {
        return "index";
    }
    @RequestMapping(value="/apple", method = RequestMethod.POST)
    public @ResponseBody Person post( @RequestBody final  Person person) {

        System.out.println(person.getfName() + " " + person.getlName());
        return person;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public
    @ResponseBody
    String listUsersJson(ModelMap model) throws JSONException {
        JSONArray userArray = new JSONArray();
        for (User user : userRepository.findAll()) {
            JSONObject userJSON = new JSONObject();
            userJSON.put("id", user.getId());
            userJSON.put("firstName", user.getFirstName());
            userJSON.put("lastName", user.getLastName());
            userJSON.put("email", user.getEmail());
            userArray.put(userJSON);
        }
        return userArray.toString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userRepository.delete(userRepository.findOne(userId));
        return "redirect:/";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("filedata") MultipartFile file,
                                                 @RequestParam("filename") String name){
        System.out.println("1");
        if (!file.isEmpty()) {
            try {
                System.out.println("2");
                byte[] bytes = file.getBytes();
                System.out.println("3");
                File ff=new File("c:\\upload\\"+name);
                System.out.println(ff.getAbsolutePath());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(ff));
                System.out.println("4");
                stream.write(bytes);
                System.out.println("5");
                stream.close();
                return "You successfully uploaded   ";
            } catch (Exception e) {
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                System.out.println(errors.toString());
                return "You failed to upload " + "" + " => " + errors.toString();
            }
        } else {
            return "You failed to upload " + "" + " because the file was empty.";
        }
    }
}