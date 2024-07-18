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
import com.ilu.loan.entities.GuaranteePicture;
import com.ilu.loan.entities.ProfilePicture;
import com.ilu.loan.entities.TransactionLoan;
import com.ilu.loan.entities.TransactionLoanDetail;
import com.ilu.loan.repositories.GuaranteePictureRepository;
import com.ilu.loan.repositories.ProfilePictureRepository;
import com.ilu.loan.repositories.TransactionLoanDetailRepository;
import com.ilu.loan.services.CustomerService;
import com.ilu.loan.services.FileService;
import com.ilu.loan.services.TransactionLoanService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ProfilePictureRepository profilePictureRepository;
    private final CustomerService customerService;
    private final GuaranteePictureRepository guaranteePictureRepository;
    private final TransactionLoanService transactionLoanService;
    private final TransactionLoanDetailRepository transactionLoanDetailRepository;

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
            TransactionLoanDetail transactionLoanDetail = null;

            if (path.equals("customers")) {
                customer = customerService.getById(id);
                url += "customers/" + id + "/avatar";
            }

            if (path.equals("transactions")) {
                TransactionLoan transactionLoan = transactionLoanService.getById(id);
                transactionLoanDetail = new TransactionLoanDetail();
                transactionLoanDetail.setTransactionLoan(transactionLoan);
                transactionLoanDetail.setTransactionDate(System.currentTimeMillis());
                transactionLoanDetail.setNominal(transactionLoan.getNominal());
                transactionLoanDetail.setLoanStatus("PAID");
                transactionLoanDetail.setCreatedAt(System.currentTimeMillis());
                transactionLoanDetail.setUpdatedAt(System.currentTimeMillis());
                
                url += "transactions/" + id + "/guarantee";
            }

            String ext = file.getOriginalFilename().split("\\.")[1];
            String fileName = UUID.randomUUID().toString() + "." + ext;
            Files.copy(file.getInputStream(), Path.of(fullPath + "/" + fileName));

            if (customer != null) {
                ProfilePicture profilePicture = new ProfilePicture();
                profilePicture.setName(fileName);
                profilePicture.setCustomer(List.of(customer));
                profilePicture.setContentType(ext);
                profilePicture.setSize(file.getSize());
                profilePicture.setUrl(url);
                profilePictureRepository.saveAndFlush(profilePicture);
            }

            if (transactionLoanDetail != null) {
                GuaranteePicture guaranteePicture = new GuaranteePicture();
                guaranteePicture.setName(fileName);
                guaranteePicture.setTransactionLoanDetail(List.of(transactionLoanDetail));
                guaranteePicture.setContentType(ext);
                guaranteePicture.setSize(file.getSize());
                guaranteePicture.setPath(url);
                guaranteePictureRepository.save(guaranteePicture);

                transactionLoanDetail.setGuaranteePicture(guaranteePicture);
                transactionLoanDetailRepository.save(transactionLoanDetail);
            }

            return FileResponse.builder().name(fileName).url(url).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}