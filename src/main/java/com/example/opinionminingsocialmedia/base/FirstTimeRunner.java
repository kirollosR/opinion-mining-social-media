package com.example.opinionminingsocialmedia.base;

import com.example.opinionminingsocialmedia.base.enums.GenderEnum;
import com.example.opinionminingsocialmedia.models.Gender;
import com.example.opinionminingsocialmedia.models.KeywordGrade;
import com.example.opinionminingsocialmedia.models.Role;
import com.example.opinionminingsocialmedia.base.enums.RoleEnum;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.payload.requests.RegisterRequest;
import com.example.opinionminingsocialmedia.payload.requests.UserRequest;
import com.example.opinionminingsocialmedia.repository.GenderRepository;
import com.example.opinionminingsocialmedia.repository.KeywordGradeRepository;
import com.example.opinionminingsocialmedia.repository.RoleRepository;
import com.example.opinionminingsocialmedia.services.AuthServices;
import com.example.opinionminingsocialmedia.services.UserServices;
import com.example.opinionminingsocialmedia.services.file_storage_service.FilesStorageService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeRunner implements CommandLineRunner {
    private final Log log = LogFactory.getLog(FirstTimeRunner.class);

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private AuthServices authServices;
    @Autowired
    private KeywordGradeRepository keywordGradeRepository;


    @Autowired
    private FilesStorageService filesStorageService;

    @Value("${myapp.isFirstRun}")
    private boolean isFirstRun;

    @Override
    public void run(String... args) throws Exception {
        if (isFirstRun) {
            log.info("when project run");
            filesStorageService.deleteAll();
            filesStorageService.init();

            roleRepository.save(Role.builder().name(RoleEnum.ADMIN.name()).build());
            roleRepository.save(Role.builder().name(RoleEnum.USER.name()).build());
            log.info("Roles saved successfully");

            genderRepository.save(Gender.builder().name(GenderEnum.MALE.name()).build());
            genderRepository.save(Gender.builder().name(GenderEnum.FEMALE.name()).build());
            log.info("Gender saved successfully");

            keywordGradeRepository.save(KeywordGrade.builder().id(2).grade("Very Negative").build());
            keywordGradeRepository.save(KeywordGrade.builder().id(4).grade("Negative").build());
            keywordGradeRepository.save(KeywordGrade.builder().id(6).grade("Neutral").build());
            keywordGradeRepository.save(KeywordGrade.builder().id(8).grade("Positive").build());
            keywordGradeRepository.save(KeywordGrade.builder().id(10).grade("Very Positive").build());
            log.info("Keyword Grades saved successfully");

            authServices.saveAdmin(RegisterRequest
                    .builder()
                    .firstName("Ziad")
                    .lastName("Khaled")
                    .gender(1)
                    .username("admin")
                    .password("admin")
                    .email("ziadkhaled@gmail.com")
                    .build());
            log.info("Admin saved successfully");
            isFirstRun = false;
        } else {
            log.info("All is saved it's not first run");
        }
    }

}