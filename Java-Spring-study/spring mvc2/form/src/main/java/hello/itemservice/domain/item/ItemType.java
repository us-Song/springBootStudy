package hello.itemservice.domain.item;

public enum ItemType {

    BOOK("도서"), Food("음식"), ETC("기타");

    private final String dscription;

    ItemType(String dscription) {
        this.dscription = dscription;
    }
}
