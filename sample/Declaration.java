package sample;

public class Declaration {
    private String idDeclaration;
    private String shipperNumber;
    private String consigneeNumber;
    private String typeDeclaration;
    private String seats;
    private String positions;
    private String paymentMethodCode;
    private String shipperCountryCode;
    private String consigneeCountryCode;
    private String declarantNumber;
    private String numberTS;
    private String grossWeight;
    private String netWeight;
    private String status;
    private String goodsCode;

    public Declaration(String idDeclaration, String shipperNumber, String consigneeNumber, String typeDeclaration,
                       String seats, String positions, String paymentMethodCode, String shipperCountryCode,
                       String consigneeCountryCode, String declarantNumber, String numberTS, String grossWeight,
                       String netWeight, String status, String goodsCode) {
        this.idDeclaration = idDeclaration;
        this.shipperNumber = shipperNumber;
        this.consigneeNumber = consigneeNumber;
        this.typeDeclaration = typeDeclaration;
        this.seats = seats;
        this.positions = positions;
        this.paymentMethodCode = paymentMethodCode;
        this.shipperCountryCode = shipperCountryCode;
        this.consigneeCountryCode = consigneeCountryCode;
        this.declarantNumber = declarantNumber;
        this.numberTS = numberTS;
        this.grossWeight = grossWeight;
        this.netWeight = netWeight;
        this.status = status;
        this.goodsCode = goodsCode;
    }
    public Declaration(){}


    public String getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(String idDeclaration) {
        this.idDeclaration = idDeclaration;
    }

    public String getShipperNumber() {
        return shipperNumber;
    }

    public void setShipperNumber(String shipperNumber) {
        this.shipperNumber = shipperNumber;
    }

    public String getConsigneeNumber() {
        return consigneeNumber;
    }

    public void setConsigneeNumber(String consigneeNumber) {
        this.consigneeNumber = consigneeNumber;
    }

    public String getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public String getShipperCountryCode() {
        return shipperCountryCode;
    }

    public void setShipperCountryCode(String shipperCountryCode) {
        this.shipperCountryCode = shipperCountryCode;
    }

    public String getConsigneeCountryCode() {
        return consigneeCountryCode;
    }

    public void setConsigneeCountryCode(String consigneeCountryCode) {
        this.consigneeCountryCode = consigneeCountryCode;
    }

    public String getDeclarantNumber() {
        return declarantNumber;
    }

    public void setDeclarantNumber(String declarantNumber) {
        this.declarantNumber = declarantNumber;
    }

    public String getNumberTS() {
        return numberTS;
    }

    public void setNumberTS(String numberTS) {
        this.numberTS = numberTS;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
}


