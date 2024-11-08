package hellojpa;

public class ValueMain {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;

        System.out.println("(a == b) = " + (a == b));

        Address address1 = new Address("city", "street", "zipCode");
        Address address2 = new Address("city", "street", "zipCode");

        System.out.println("(a == b) = " + (address1 == address2)); // address1 과 address2 의 주소는 다르니까 당연히 false
        System.out.println("(a == b) = " + address1.equals(address2)); //address1 과 address2의 주소를 비교하는게 아니라필드 "값"을 하나 하나 비교
    }
}
