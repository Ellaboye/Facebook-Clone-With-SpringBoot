package com.example.week7.serviceImplementation;

import com.example.week7.Services.UsersService;
import com.example.week7.model.Users;
import com.example.week7.repository.UsersRepository;
import com.example.week7.util.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;
    /**
     * CREATE operation on User
     * @param users
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean createUser(Users users){
        boolean correct = false;

        try {
            //password encryption
            users.setPassword(PasswordHashing.encryptPassword(users.getPassword()));

            Users userData = usersRepository.findPersonByEmail(users.getEmail());

            if (userData == null){
                System.out.println(users);
                usersRepository.save(users);
                correct = true;
            } else correct = false;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return correct;
    }

    /**
     * Get operation on User
     * @param email
     * @param password
     * @return User object
     * */
    public Users getUser(String email, String password){
        Users userData = null;

        try{
            userData = usersRepository.findPersonByEmail(email);

            if(!password.equals(PasswordHashing.decryptPassword(userData.getPassword()))){
                userData = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return userData;
    }
}
