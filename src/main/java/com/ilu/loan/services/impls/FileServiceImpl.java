package com.ilu.loan.services.impls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ilu.loan.dto.response.FileResponse;
import com.ilu.loan.entities.Customer;
import com.ilu.loan.entities.ProfilePicture;
import com.ilu.loan.repositories.ProfilePictureRepository;
import com.ilu.loan.services.CustomerService;
import com.ilu.loan.services.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ProfilePictureRepository profilePictureRepository;
    private final CustomerService customerService;

    public final String UPLOAD_DIRECTORY = "src/main/java/com/ilu/loan/assets/";
    public final String API_ENDPOINT = "/api/";

    @Override
    public boolean deleteFile(String fileName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public byte[] downloadFile(String fileName) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFileExists(String path, String fileName) {
        Path fullPath = Path.of(UPLOAD_DIRECTORY + path + "/" + fileName);
        return Files.exists(fullPath);
    }

    @Override
    public byte[] showFile(String fileName) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FileResponse storeFile(MultipartFile file, String path, String id) throws IOException {
        String fullPath = UPLOAD_DIRECTORY + path;

        if (!Files.exists(Path.of(fullPath))) {
            try {
                Files.createDirectories(Path.of(fullPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            String url = API_ENDPOINT;
            Customer customer = null;

            if (path.equals("customers")) {
                customer = customerService.getById(id);
                url += "customers/" + id + "/avatar";
            }

            String ext = file.getOriginalFilename().split("\\.")[1];
            String fileName = UUID.randomUUID().toString() + "." + ext;
            Files.copy(file.getInputStream(), Path.of(fullPath + "/" + fileName));


            System.out.println("=========================================");
            System.out.println("Customer: " + customer);
            System.out.println("File Name: " + fileName);
            System.out.println("=========================================");

            if (customer != null) {
                ProfilePicture profilePicture = new ProfilePicture();
                profilePicture.setName(fileName);
                profilePicture.setCustomer(List.of(customer));
                profilePicture.setContentType(ext);
                profilePicture.setSize(file.getSize());
                profilePicture.setUrl(url);

                System.out.println("=========================================");
                System.out.println("Profile Picture: " + profilePicture);
                System.out.println("=========================================");
                profilePictureRepository.saveAndFlush(profilePicture);
            }

            return FileResponse.builder().name(fileName).url(url).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}