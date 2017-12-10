package au.com.unico.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by xiaofei on 2017/12/10.
 */
@Entity
public class Gcd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer number1;
    private Integer number2;
    // Greatest Common Divisor (GCD) of number1 and number2
    private Integer gcdNum;

    public Gcd() {
    }

    public Gcd(Integer number1, Integer number2, Integer gcdNum) {
        this.number1 = number1;
        this.number2 = number2;
        this.gcdNum = gcdNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public Integer getGcdNum() {
        return gcdNum;
    }

    public void setGcdNum(Integer gcdNum) {
        this.gcdNum = gcdNum;
    }
}
