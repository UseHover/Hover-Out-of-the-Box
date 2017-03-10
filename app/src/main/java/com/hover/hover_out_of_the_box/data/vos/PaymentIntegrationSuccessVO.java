package com.hover.hover_out_of_the_box.data.vos;

/**
 * Created by aung on 3/2/17.
 */

public class PaymentIntegrationSuccessVO {

    private int serviceId;
    private String serviceName;
    private String operatorName;
    private String countryName;
    private String currency;

    private PaymentIntegrationSuccessVO(int serviceId, String serviceName, String operatorName, String countryName, String currency) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.operatorName = operatorName;
        this.countryName = countryName;
        this.currency = currency;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public static PaymentIntegrationSuccessVO newPaymentIntegrationSuccess(int serviceId, String serviceName, String operatorName, String countryName, String currency) {
        return new PaymentIntegrationSuccessVO(serviceId, serviceName, operatorName, countryName, currency);
    }
}
