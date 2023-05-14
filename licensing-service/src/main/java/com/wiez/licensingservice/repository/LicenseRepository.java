package com.wiez.licensingservice.repository;

import com.wiez.licensingservice.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenseRepository extends CrudRepository<License, String> {
    public Optional<List<License>> findByOrganizationId(String organizationId);

    public Optional<License> findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

    public Boolean deleteByLicenseId(String licenseId);

}