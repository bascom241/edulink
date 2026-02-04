package com.Edulink.EdulinkServer.service;

import com.Edulink.EdulinkServer.config.CloudinaryConfig;
import com.Edulink.EdulinkServer.dao.UserPrincipal;
import com.Edulink.EdulinkServer.dao.UserRepository;
import com.Edulink.EdulinkServer.dto.user.EditBankDetail;
import com.Edulink.EdulinkServer.dto.user.EditProfileRequest;
import com.Edulink.EdulinkServer.dto.user.UserRequestDTO;
import com.Edulink.EdulinkServer.dto.user.UserResponseDTO;
import com.Edulink.EdulinkServer.model.Bank;
import com.Edulink.EdulinkServer.model.User;
import com.Edulink.EdulinkServer.repository.BankRepository;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryConfig cloudinaryConfig;

    @Autowired
    private UserResponseDTO userResponseDTO;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PayStackService payStackService;



    @Autowired
    private BankRepository bankRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new RuntimeException("User Not Found");
        }
        return new UserPrincipal(user);
    }


private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO,
                                        MultipartFile certificate
                                        ) {
        try {
            User user = modelMapper.map(userRequestDTO, User.class);

            // Password validation
            String password = userRequestDTO.getPassword();
            String confirmPassword = userRequestDTO.getConfirmPassword();

            if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("Password cannot be empty");
            }
            if (confirmPassword == null || confirmPassword.isBlank()) {
                throw new IllegalArgumentException("Confirm password cannot be empty");
            }
            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not match");
            }

            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setConfirmPassword(bCryptPasswordEncoder.encode(confirmPassword));

            // ✅ Upload only if teacher
            if (user.isTeacher()) {
                if (certificate == null ){
                    throw new IllegalArgumentException("Teacher must provide certificate and government ID");
                }

                Map<String, Object> certificateUploadResult =
                        cloudinaryConfig.cloudinary().uploader().upload(certificate.getBytes(), ObjectUtils.emptyMap());


                String certificateUrl = (String) certificateUploadResult.get("secure_url");


                user.setCertificateUrl(certificateUrl);
                user.setCertificateImageName(certificate.getOriginalFilename());
                user.setCertificateImageType(certificate.getContentType());
                user.setRole("ROLE_TEACHER");
            } else {
                user.setRole("ROLE_STUDENT");
            }

            User savedUser = userRepository.save(user);

            // Build response
            UserResponseDTO response = new UserResponseDTO();
            response.setUserId(savedUser.getUserId());
            response.setFirstName(savedUser.getFirstName());
            response.setCertificateUrl(savedUser.getCertificateUrl());
            response.setStudent(savedUser.isStudent());
            response.setTeacher(savedUser.isTeacher());
            response.setSctaPoints(savedUser.getSctaPoints());
            response.setEmail(savedUser.getEmail());
            response.setPhoneNumber(savedUser.getPhoneNumber());
            response.setShortBio(savedUser.getShortBio());

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    public UserResponseDTO editProfile (String email, EditProfileRequest userData ){
        User user = userRepository.findByEmail(email);
        if(user == null ){
            throw new UsernameNotFoundException("user not found");
        }




        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setShortBio(userData.getShortBio());
        user.setSocialLink(userData.getSocialLink());

        User savedUser  = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setUserId(savedUser.getUserId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());

        response.setPhoneNumber(savedUser.getPhoneNumber());
        response.setShortBio(savedUser.getShortBio());



        return response;

    }

    private Bank getBankBySlug(String slug) {
        List<Bank> banks = bankRepository.findAllBySlugIgnoreCase(slug);
        if (banks.isEmpty()) throw new RuntimeException("Bank not found");
        return banks.get(0); // pick the first one
    }



    @Transactional
    public UserResponseDTO editBankDetail(String email, EditBankDetail editBankDetail) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        if (user.isStudent()) {
            throw new IllegalArgumentException("Only teachers can edit bank details");
        }

        if (editBankDetail.getSlug() == null || editBankDetail.getBankAccount() == null) {
            throw new RuntimeException("Bank and account number must be provided");
        }

        Bank bank = getBankBySlug(editBankDetail.getSlug());
        String subAccountCode = null;

        if (user.getSubAccountCode() == null || "null".equals(user.getSubAccountCode())) {
           subAccountCode = payStackService.createSubAccount(
                    user.getFirstName(),
                    bank.getCode(),
                    editBankDetail.getBankAccount(),
                    90
            );

        }
        user.setSubAccountCode(subAccountCode);
        user.setBankName(bank.getName());
        user.setBankAccount(editBankDetail.getBankAccount());
        user.setBankCode(bank.getCode());

        User savedUser = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setBankName(savedUser.getBankName());
        response.setBankAccount(savedUser.getBankAccount());
        response.setSubAccountCode(savedUser.getSubAccountCode());

        return response;
    }





}
