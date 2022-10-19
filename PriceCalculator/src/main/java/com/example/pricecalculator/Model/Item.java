package com.example.pricecalculator.Model;

import javax.persistence.*;






    @Entity
    @Table(name = "Price")
    public class Item{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        private String itemName;


        private Integer noOfUnitsInCartoon;


        private Double priceOFSingleCartoon;


        private String imageUrl;


        private Double increasedPrecentage = 0.3;


        private Double discountPrecentage = 0.1;


        private Integer minCartoonAmountToDiscount = 3;

        public String getItemName() {
            return itemName;
        }

        private void setItemName() {
            this.itemName = itemName;
        }

        public Integer getMinCartoonAmountToDiscount() {
            return minCartoonAmountToDiscount;
        }

        public void setMinCartoonAmountToDiscount(Integer minCartoonAmountToDiscount) {
            this.minCartoonAmountToDiscount = minCartoonAmountToDiscount;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getNoOfUnitsInCartoon() {
            return noOfUnitsInCartoon;
        }

        public void setNoOfUnitsInCartoon(Integer noOfUnitsInCartoon) {
            this.noOfUnitsInCartoon = noOfUnitsInCartoon;
        }

        public Double getPriceOFSingleCartoon() {
            return priceOFSingleCartoon;
        }

        public void setPriceOFSingleCartoon(Double priceOFSingleCartoon) {
            this.priceOFSingleCartoon = priceOFSingleCartoon;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Double getIncreasedPrecentage() {
            return increasedPrecentage;
        }

        public void setIncreasedPrecentage(Double increasedPrecentage) {
            this.increasedPrecentage = increasedPrecentage;
        }

        public Double getDiscountPrecentage() {
            return discountPrecentage;
        }

        public void setDiscountPrecentage(Double discountPrecentage) {
            this.discountPrecentage = discountPrecentage;
        }

        public Double calculateSingleUnitPrice() {
            return (this.priceOFSingleCartoon / this.noOfUnitsInCartoon) + (this.priceOFSingleCartoon / this.noOfUnitsInCartoon * this.increasedPrecentage);
        }

        public void setItemName(String itemName) {
        }
    }

