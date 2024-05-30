package org.springboot.busreservationapi.dao;

import org.springboot.busreservationapi.model.Admin;
import org.springboot.busreservationapi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminDao {

    @Autowired
    private AdminRepository adminRepository;

    //-------------------For adding New Admin---------------
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    //-------------------Find Admin By Id---------------
    public Optional<Admin> findAdminById(int adminId){
        return adminRepository.findById(adminId);
    }

    //-------------------Find All Admin ---------------
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    //-------------------Update Admin----------------
    public Admin updateAdmin(Admin existingAdmin) {
        return adminRepository.save(existingAdmin);
    }

    //-------------------Delete Admin by Id------------
    public void delete(int adminId) {
        adminRepository.deleteById(adminId);
    }

    //------------------Verify By Phone and Password----------------
    public Optional<Admin> verifyAdminByPhoneAndPassword(long phone, String password) {
        return adminRepository.findByPhoneAndPassword(phone, password);
    }

    //------------------Verify By Email and Password----------------
    public Optional<Admin> verifyAdminByEmailAndPassword(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }


}
