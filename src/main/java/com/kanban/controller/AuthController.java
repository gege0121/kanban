package com.kanban.controller;

import com.kanban.model.Role;
import com.kanban.model.User;
import com.kanban.service.JWTService;
import com.kanban.service.RoleService;
import com.kanban.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private UserService userService;
    @Autowired private RoleService roleService;
    @Autowired private JWTService jwt;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody User user){
        User u = userService.getUserByCredential(user.getName());
        if(u==null) return ResponseEntity.status(401).build();
        if(u.getPassword().equals(user.getPassword())){
            Map<String,String> r = new HashMap<String, String>();
            r.put("jwt",jwt.generateToken(u));
            return ResponseEntity.ok().body(r);
        }else{
            return ResponseEntity.status(401).build();
        }
    }

    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody User user){
        try {
            User u = userService.getUserByCredential(user.getName());
            if(u!=null) return ResponseEntity.ok().body("User name or email is already registered.");

            List<Role> roles = new ArrayList<Role>();
            Role userRole = roleService.getById(1);
            roles.add(userRole);
            user.setRoles(roles);
            User r = userService.save(user);
            Map rmap = new HashMap();
            rmap.put("name",r.getName());
            rmap.put("email",r.getEmail());
            rmap.put("status","success");
            if(r==null) return ResponseEntity.ok().body("Fail to registered.");
            else return ResponseEntity.ok().body(rmap);

        }catch (Exception e){
            e.printStackTrace();

        }

        return ResponseEntity.ok().body("Fail to registered.");
    }


}