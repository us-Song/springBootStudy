package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode //equals랑 hashCode 메서드를 자동 구현
//equals는 두 객체의 내용이 같은지, 동등성(equality) 를 비교하는 연산자
//hashCode는 두 객체가 같은 객체인지, 동일성(identity) 를 비교하는 연산자 , 주소 상관없이 객체 내부 값만을 비교해서 판단
public class IpPort {
    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}


