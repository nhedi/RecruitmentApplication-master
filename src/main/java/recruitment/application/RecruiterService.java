package recruitment.application;

import recruitment.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruiterService {
    @Autowired
    private RecruiterRepository recruiterRepo;

    public String findRole(int id) {
        return recruiterRepo.findRoleById(1);
    }

    public String findName(int id){
        return recruiterRepo.findNameById(id);
    }

    public String findCompetence(int id){
        return recruiterRepo.findCompetenceById(2);
    }

    public String findAvailabilityByPid(int pid){
        return recruiterRepo.findAvailabilityByPid(pid).toString();
    }

    public BigDecimal getExperienceByPid(int pid){
        return recruiterRepo.getExperienceByPid(pid);
    }

    public boolean checkUsername(String username) {
        return recruiterRepo.checkUsername(username);
    }

    public boolean checkEmail(String email) {
        return recruiterRepo.checkEmail(email);
    }

    public boolean checkSsn(String ssn) {
        return recruiterRepo.checkSsn(ssn);
    }

    public void registerUser(String fname, String lname, String email, String ssn, String username, String password) {
        recruiterRepo.registerUser(fname, lname, ssn, email, password, 2, username);
    }


//    public void setNewRate(double value, String fromto) {
//        recruiterRepo.setNewRate(value, fromto);
//    }
//
//    public void setNewCount(int count, String fromto) {
//        recruiterRepo.setNewCount(count, fromto);
//    }
//
//    public int countSum() {
//        return recruiterRepo.countSum();
//    }
}
