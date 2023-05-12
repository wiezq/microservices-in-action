package com.wiez.licensingservice.service;

import com.wiez.licensingservice.model.License;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@Service
public class LicenseService {


    MessageSource messages;

    public LicenseService(MessageSource messages) {
        this.messages = messages;
    }

    public License getLicense(String licenseId, String organizationId){
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");

        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale){
        String responseMessage = null;
        if(Objects.nonNull(license)) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.create.message",null,locale), license);
        }

        return responseMessage;
    }

    public String updateLicense(License license, String organizationId){
        String responseMessage = null;
        if(Objects.nonNull(license)) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.update.message", null, null), license);
        }

        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId){
        return String.format(messages.getMessage("license.delete.message", null, null),licenseId, organizationId);

    }
}