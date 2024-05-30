package org.springboot.busreservationapi.controller;

import jakarta.validation.Valid;
import org.springboot.busreservationapi.dto.AdminRequest;
import org.springboot.busreservationapi.dto.AdminResponse;
import org.springboot.busreservationapi.dto.ResponseStructure;
import org.springboot.busreservationapi.model.Admin;
import org.springboot.busreservationapi.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Adds a new admin.
     *
     * @param adminRequest the admin request
     * @return the response entity with the added admin
     */
    @PostMapping
    public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        return adminService.addAdmin(adminRequest);
    }

    /**
     * Finds an admin by ID.
     *
     * @param adminId the admin ID
     * @return the response entity with the found admin
     */
    @GetMapping("/{adminId}")
    public ResponseEntity<ResponseStructure<AdminResponse>> findAdminById(@PathVariable int adminId) {
        return adminService.findAdminById(adminId);
    }

    /**
     * Finds all admins.
     *
     * @return the response entity with the list of all admins
     */
    @GetMapping
    public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins() {
        return adminService.findAllAdmins();
    }

    /**
     * Updates an existing admin.
     *
     * @param adminId      the admin ID
     * @param updatedAdmin the updated admin details
     * @return the response entity with the updated admin
     */
    @PutMapping("/{adminId}")
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@PathVariable int adminId, @RequestBody Admin updatedAdmin) {
        return adminService.updateAdmin(adminId, updatedAdmin);
    }

    /**
     * Deletes an admin by ID.
     *
     * @param adminId the admin ID
     * @return the response entity with a confirmation message
     */
    @DeleteMapping("/{adminId}")
    public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable int adminId) {
        return adminService.deleteAdmin(adminId);
    }

    /**
     * Verifies admin by email and password.
     *
     * @param email    the admin email
     * @param password the admin password
     * @return the response entity with the verified admin
     */
    @PostMapping("/verify/email")
    public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdminByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return adminService.verifyAdminByEmailAndPassword(email, password);
    }

    /**
     * Verifies admin by phone and password.
     *
     * @param phone    the admin phone number
     * @param password the admin password
     * @return the response entity with the verified admin
     */
    @PostMapping("/verify/phone")
    public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdminByPhoneAndPassword(@RequestParam long phone, @RequestParam String password) {
        return adminService.verifyAdminByPhoneAndPassword(phone, password);
    }
}
