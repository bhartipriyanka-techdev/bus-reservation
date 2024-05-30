package org.springboot.busreservationapi.services;

import org.springboot.busreservationapi.Exception.AdminNotFoundException;
import org.springboot.busreservationapi.dao.AdminDao;
import org.springboot.busreservationapi.dto.AdminRequest;
import org.springboot.busreservationapi.dto.AdminResponse;
import org.springboot.busreservationapi.dto.ResponseStructure;
import org.springboot.busreservationapi.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * Method to map AdminRequest to Admin.
     *
     * @param adminRequest the admin request
     * @return the mapped Admin object
     */
    private Admin mapToAdmin(AdminRequest adminRequest) {
        return Admin.builder()
                .adminName(adminRequest.getAdminName())
                .adminEmailId(adminRequest.getAdminEmailId())
                .adminGstNo(adminRequest.getAdminGstNo())
                .adminPhoneNo(adminRequest.getAdminPhoneNo())
                .travelName(adminRequest.getTravelName())
                .adminPassword(adminRequest.getAdminPassword())
                .build();
    }

    /**
     * Method to map Admin to AdminResponse.
     *
     * @param admin the admin
     * @return the mapped AdminResponse object
     */
    private AdminResponse mapToAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .adminName(admin.getAdminName())
                .adminEmailId(admin.getAdminEmailId())
                .adminGstNo(admin.getAdminGstNo())
                .adminPhoneNo(admin.getAdminPhoneNo())
                .travelName(admin.getTravelName())
                .adminPassword(admin.getAdminPassword())
                .build();
    }

    /**
     * Adds a new admin.
     *
     * @param adminRequest the admin request
     * @return the response entity with the added admin
     */
    public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest) {
        ResponseStructure<AdminResponse> responseStructure = new ResponseStructure<>();
        Admin addedAdmin = adminDao.addAdmin(mapToAdmin(adminRequest));
        responseStructure.setMessage("Admin Added Successfully!");
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setData(mapToAdminResponse(addedAdmin));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
    }

    /**
     * Finds an admin by ID.
     *
     * @param adminId the admin ID
     * @return the response entity with the found admin
     * @throws AdminNotFoundException if the admin is not found
     */
    public ResponseEntity<ResponseStructure<AdminResponse>> findAdminById(int adminId) {
        ResponseStructure<AdminResponse> responseStructure = new ResponseStructure<>();
        Optional<Admin> rcvAdmin = adminDao.findAdminById(adminId);
        if (rcvAdmin.isPresent()) {
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToAdminResponse(rcvAdmin.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new AdminNotFoundException("Please check, you entered an invalid Admin ID");
    }

    /**
     * Finds all admins.
     *
     * @return the response entity with the list of all admins
     */
    public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins() {
        ResponseStructure<List<AdminResponse>> structure = new ResponseStructure<>();
        List<AdminResponse> adminResponses = adminDao.findAll().stream()
                .map(this::mapToAdminResponse)
                .collect(Collectors.toList());
        structure.setData(adminResponses);
        structure.setMessage("List of All Admins");
        structure.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(structure);
    }

    /**
     * Updates an existing admin.
     *
     * @param adminId      the admin ID
     * @param updatedAdmin the updated admin details
     * @return the response entity with the updated admin
     * @throws AdminNotFoundException if the admin is not found
     */
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(int adminId, Admin updatedAdmin) {
        ResponseStructure<AdminResponse> responseStructure = new ResponseStructure<>();
        Optional<Admin> existingAdminOptional = adminDao.findAdminById(adminId);

        if (existingAdminOptional.isPresent()) {
            Admin existingAdmin = existingAdminOptional.get();
            existingAdmin.setAdminName(updatedAdmin.getAdminName());
            existingAdmin.setAdminEmailId(updatedAdmin.getAdminEmailId());
            existingAdmin.setAdminPhoneNo(updatedAdmin.getAdminPhoneNo());
            existingAdmin.setAdminPassword(updatedAdmin.getAdminPassword());
            existingAdmin.setAdminGstNo(updatedAdmin.getAdminGstNo());
            Admin savedAdmin = adminDao.updateAdmin(existingAdmin);

            responseStructure.setMessage("Admin Updated Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToAdminResponse(savedAdmin));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new AdminNotFoundException("Please check, you entered an invalid Admin ID");
    }

    /**
     * Deletes an admin by ID.
     *
     * @param adminId the admin ID
     * @return the response entity with a confirmation message
     * @throws AdminNotFoundException if the admin is not found
     */
    public ResponseEntity<ResponseStructure<String>> deleteAdmin(int adminId) {
        ResponseStructure<String> structure = new ResponseStructure<>();
        Optional<Admin> recAdmin = adminDao.findAdminById(adminId);
        if (recAdmin.isPresent()) {
            structure.setData("Admin Found");
            structure.setMessage("Admin deleted Successfully!");
            structure.setStatusCode(HttpStatus.OK.value());
            adminDao.delete(adminId);
            return ResponseEntity.status(HttpStatus.OK).body(structure);
        }
        throw new AdminNotFoundException("Please check, you entered an invalid Admin ID");
    }

    /**
     * Verifies admin by email and password.
     *
     * @param email    the admin email
     * @param password the admin password
     * @return the response entity with the verified admin
     * @throws AdminNotFoundException if the admin is not found
     */
    public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdminByEmailAndPassword(String email, String password) {
        ResponseStructure<AdminResponse> responseStructure = new ResponseStructure<>();
        Optional<Admin> rcvAdmin = adminDao.verifyAdminByEmailAndPassword(email, password);
        if (rcvAdmin.isPresent()) {
            responseStructure.setMessage("Admin Found");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToAdminResponse(rcvAdmin.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new AdminNotFoundException("Please check, you entered an invalid Admin email or password");
    }

    /**
     * Verifies admin by phone and password.
     *
     * @param phone    the admin phone number
     * @param password the admin password
     * @return the response entity with the verified admin
     * @throws AdminNotFoundException if the admin is not found
     */
    public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdminByPhoneAndPassword(long phone, String password) {
        ResponseStructure<AdminResponse> responseStructure = new ResponseStructure<>();
        Optional<Admin> rcvAdmin = adminDao.verifyAdminByPhoneAndPassword(phone, password);
        if (rcvAdmin.isPresent()) {
            responseStructure.setMessage("Admin Found");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToAdminResponse(rcvAdmin.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new AdminNotFoundException("Please check, you entered an invalid Admin phone number or password");
    }
}
