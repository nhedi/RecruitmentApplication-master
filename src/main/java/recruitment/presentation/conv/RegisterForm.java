package recruitment.presentation.conv;

import recruitment.util.Util;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

class RegisterForm {

    @NotBlank(message = "First name may not be blank.")
    private String fname;

    @NotBlank(message = "Last name may not be blank.")
    private String lname;

    @Email(message = "Please submit a valid email address.")
    @NotBlank(message = "Please fill in your email address.")
    private String email;

    @DateTimeFormat(pattern = "YY/MM/DD")
    @NotBlank(message = "Social security number may not be blank.")
    private String ssn;

    @NotBlank(message = "Please choose a username.")
    private String username;

    @NotBlank(message = "Please choose a password.")
    private String password;

    @NotBlank(message = "Please confirm your password.")
    private String confirmPwd;

    private String from;
    private String to;
    private String conversionResult;
    private int totalCount;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }


    public int getTotalCount() { return totalCount; }

    public void setTotalCount(int count) {this.totalCount = count; }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult + " " + this.to;
    }

    public String getConversionResult() {
        return conversionResult;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }
}
