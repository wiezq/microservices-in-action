package com.wiez.licensingservice.service;

import com.wiez.licensingservice.config.ServiceConfig;
import com.wiez.licensingservice.model.License;
import com.wiez.licensingservice.repository.LicenseRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {


    private final MessageSource messages;

    private final LicenseRepository licenseRepository;

    private final ServiceConfig config;

    public LicenseService(MessageSource messages, LicenseRepository licenseRepository, ServiceConfig config) {
        this.messages = messages;
        this.licenseRepository = licenseRepository;
        this.config = config;
    }

    public License getLicense(String licenseId, String organizationId) {
        return licenseRepository
                .findByOrganizationIdAndLicenseId(organizationId, licenseId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                messages.getMessage(
                                        "license.search.error.message",
                                        null,
                                        null)))
                .withComment(config.getProperty());
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());

    }

    public License updateLicense(License license) {
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId) {
        licenseRepository.deleteByLicenseId(licenseId);
        return String.format(
                messages.getMessage(
                        "license.delete.message",
                        null,
                        null), licenseId);

    }
}