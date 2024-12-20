package com.batu.grpc.domain.entity;

import com.batu.common.domain.entity.AggregateRoot;
import com.batu.common.domain.valueobject.BookStatus;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.domain.valueObject.BookId;
import com.batu.common.domain.valueobject.Money;
import com.batu.grpc.domain.exception.BookDomainException;
import com.batu.grpc.domain.valueObject.SkuCode;

import java.util.List;
import java.util.UUID;
public class Book extends AggregateRoot<BookId> {
    private final Integer pageSize;
    private final String title;

    private SkuCode skuCode;
    private AuthorId authorId;
    private Money price;
    private BookStatus bookStatus;
    private List<String> failureMessages;


    public Book(BookId bookId, AuthorId authorId, SkuCode skuCode, Integer pageSize, String title, Money price, List<String> failureMessages) {
        this.title = title;
        super.setId(bookId);
        this.authorId = authorId;
        this.skuCode = skuCode;
        this.pageSize = pageSize;
        this.price = price;
        this.failureMessages = failureMessages;
    }
    // Every book must be initialized for Book Entity when the Author processed in the DomainService!
    public void initializeBook(AuthorId authorId){
        super.setId(new BookId(UUID.randomUUID()));
        this.authorId = authorId;
        this.skuCode = new SkuCode(UUID.randomUUID());
        this.bookStatus = BookStatus.INITIALIZATION;
    }


    public void validateBook(){
        validateInitialBook();
        validatePageSize();
        validatePrice();
    }

    public void changeStockInfo(){
        if(this.bookStatus == BookStatus.IN_STOCK){
            this.bookStatus = BookStatus.NOT_IN_STOCK;
        } else if (this.bookStatus == BookStatus.NOT_IN_STOCK){
            this.bookStatus = BookStatus.IN_STOCK;
        }
    }

    public void updatePrice(Money val){
        validatePrice();
        this.price = val;
    }

    private void validateInitialBook(){
        if(bookStatus == null || super.getId() != null){
            throw new BookDomainException("Book is not in a correct state for initialization!");
        }
    }

    private void validatePageSize(){
        if(this.pageSize == null || this.pageSize <= 0){
            throw new BookDomainException("Page size must be higher then zero or not be null!");
        }
    }

    private void validatePrice(){
        if(!this.price.isGreaterThenZero() || this.price.getAmount() == null){
            throw new BookDomainException("Price is not higher then zero or null in the book!");
        }
    }

    private Book(Builder builder) {
        super.setId(builder.bookId);
        authorId = builder.authorId;
        skuCode = builder.skuCode;
        bookStatus = builder.bookStatus;
        title = builder.title;
        pageSize = builder.pageSize;
        price = builder.price;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public AuthorId getAuthorId() {
        return authorId;
    }

    public SkuCode getSkuCode() {
        return skuCode;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Money getPrice() {
        return price;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }


    public static final class Builder {
        private BookId bookId;
        private AuthorId authorId;
        private SkuCode skuCode;
        private String title;
        private BookStatus bookStatus;
        private Integer pageSize;
        private Money price;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder bookId(BookId val) {
            bookId = val;
            return this;
        }

        public Builder authorId(AuthorId val) {
            authorId = val;
            return this;
        }

        public Builder skuCode(SkuCode val) {
            skuCode = val;
            return this;
        }

        public Builder title(String val){
            title = val;
            return this;
        }

        public Builder bookStatus(BookStatus val){
            bookStatus = val;
            return this;
        }

        public Builder pageSize(Integer val) {
            pageSize = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
