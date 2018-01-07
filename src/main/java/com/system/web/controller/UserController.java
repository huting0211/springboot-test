package com.system.web.controller;

import com.system.web.domain.User;
import com.system.web.infrastructure.common.*;
import com.system.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(path = {"/", ""}, method = RequestMethod.GET)
    public String index() {
        return "/user/index";
    }

    @ResponseBody
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public PagedList<User> list(PageSearchModel searchModel) {
        return userService.getPagedList(searchModel);
    }

    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam(value = "username", required = false) String username) {
        FormModel<User> formModel = new FormModel<>();
        if (null == username || "".equals(username)) {
            formModel.setFormModel(new User());
            formModel.setFormType(FormType.Add);
        } else {
            User user = userService.getByUsername(username);
            if (user == null) {
                formModel.setFormType(FormType.Error);
                formModel.setMessage("无法找到用户" + username);
            } else {
                formModel.setFormType(FormType.Edit);
                formModel.setFormModel(user);
            }
        }
        ModelAndView modelAndView = new ModelAndView("/user/detail");
        modelAndView.addObject("model", formModel);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public OperationResult<User> add(User user) {
        int count = userService.addUser(user);
        return getResult(user, count);
    }

    @ResponseBody
    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public OperationResult<User> edit(User user) {
        int count = userService.updateUser(user);
        return getResult(user, count);
    }

    private OperationResult<User> getResult(User user, int count) {
        OperationResult<User> result = new OperationResult<>();
        if (count > 0) {
            result.setStatus(OperationStatus.Success);
            result.setMessage("保存用户数据成功");
            result.setObject(user);
        } else {
            result.setStatus(OperationStatus.Failed);
            result.setMessage("保存用户数据失败");
            result.setObject(user);
        }
        return result;
    }
}
