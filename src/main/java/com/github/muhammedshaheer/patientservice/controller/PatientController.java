package com.github.muhammedshaheer.patientservice.controller;

import com.github.muhammedshaheer.patientservice.dto.PatientDTO;
import com.github.muhammedshaheer.patientservice.model.Patient;
import com.github.muhammedshaheer.patientservice.service.PatientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;
    private final ModelMapper modelMapper;

    public PatientController(PatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/patients-add")
    public String showAddPatientPage(Model model) {
        logger.info("New request received to view add patient page");
        logger.info("Request processed successfully for viewing add patient record");
        return "add-patient";
    }

    @PostMapping("/patients/add")
    public String addPatientRecord(PatientDTO patientDTO, Model model) {
        logger.info("New request received to add new patient record");
        if (StringUtils.isEmpty(patientDTO.getFullName())) {
            logger.error("Add Patient | Name is required");
            model.addAttribute("error", "Name is required");
            model.addAttribute("patient", patientDTO);
            return "add-patient";
        }
        if (patientDTO.getFullName().length() > 200) {
            logger.error("Add Patient | Name must be less than 200 characters");
            model.addAttribute("patient", patientDTO);
            model.addAttribute("error", "Name must be less than 200 characters");
            return "add-patient";
        }
        if (StringUtils.isEmpty(patientDTO.getPhoneNumber())) {
            logger.error("Add Patient | PhoneNumber is required");
            model.addAttribute("patient", patientDTO);
            model.addAttribute("error", "PhoneNumber is required");
            return "add-patient";
        } else {
            Pattern pattern = Pattern.compile("(^$|[0-9]{10})");
            Matcher matcher = pattern.matcher(patientDTO.getPhoneNumber());
            if (!matcher.matches()) {
                logger.error("Add Patient | PhoneNumber isn't valid");
                model.addAttribute("patient", patientDTO);
                model.addAttribute("error", "PhoneNumber isn't valid");
                return "add-patient";
            }
        }
        Patient patient = convertPatientDTOToPatient(patientDTO);
        patientService.addPatientRecord(patient);
        logger.info("Request processed successfully for adding new patient record");
        return "redirect:/";
    }

    @GetMapping("/")
    public String getAllPatientsRecords(Model model,
                                        @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                        @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                        @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        logger.info("New request received to fetch all patient records | keyword: {}", keyword);
        List<Patient> patients = patientService.getAllPatientsRecords(keyword, offset, limit);
        List<PatientDTO> patientDTOList = patients.stream()
                .map(this::convertPatientToPatientDTO)
                .collect(Collectors.toList());
        model.addAttribute("patients", patientDTOList);
        logger.info("Request processed successfully for fetching all patient records | keyword: {}", keyword);
        return "home";
    }

    @GetMapping("/patients/{id}")
    public String getPatientRecord(Model model, @PathVariable(value = "id") String id) {
        logger.info("New request received to fetch patient record | patientId: {}", id);
        Patient patient = patientService.getPatientRecord(id);
        PatientDTO patientDTO = convertPatientToPatientDTO(patient);
        model.addAttribute("patient", patientDTO);
        logger.info("Request processed successfully for fetching patient record | patientId: {}", id);
        return "patient-details";
    }

    @PostMapping("/patients/delete/{id}")
    public String deletePatientRecord(Model model, @PathVariable(value = "id") String id) {
        logger.info("New request received to remove patient record | patientId: {}", id);
        patientService.deletePatientRecord(id);
        logger.info("Request processed successfully for removing patient record | patientId: {}", id);
        return "redirect:/";
    }

    @GetMapping("/patients/edit/{id}")
    public String showEditPatientPage(Model model, @PathVariable(value = "id") String id) {
        logger.info("New request received to view edit patient page | patientId: {}", id);
        Patient patient = patientService.getPatientRecord(id);
        PatientDTO patientDTO = convertPatientToPatientDTO(patient);
        model.addAttribute("patient", patientDTO);
        logger.info("Request processed successfully for redirecting to editing patient record | patientId: {}", id);
        return "edit-patient";
    }

    @PostMapping("/patients/update/{id}")
    public String updatePatientRecord(@PathVariable("id") String id, PatientDTO patientDTO, Model model) {
        logger.info("New request received to edit patient record | patientId: {}", id);
        if (StringUtils.isEmpty(patientDTO.getFullName())) {
            logger.error("Add Patient | Name is required");
            model.addAttribute("error", "Name is required");
            model.addAttribute("patient", patientDTO);
            return "edit-patient";
        }
        if (patientDTO.getFullName().length() > 200) {
            logger.error("Add Patient | Name must be less than 200 characters");
            model.addAttribute("patient", patientDTO);
            model.addAttribute("error", "Name must be less than 200 characters");
            return "edit-patient";
        }
        if (StringUtils.isEmpty(patientDTO.getPhoneNumber())) {
            logger.error("Add Patient | PhoneNumber is required");
            model.addAttribute("patient", patientDTO);
            model.addAttribute("error", "PhoneNumber is required");
            return "edit-patient";
        } else {
            Pattern pattern = Pattern.compile("(^$|[0-9]{10})");
            Matcher matcher = pattern.matcher(patientDTO.getPhoneNumber());
            if (!matcher.matches()) {
                logger.error("Add Patient | PhoneNumber isn't valid");
                model.addAttribute("patient", patientDTO);
                model.addAttribute("error", "PhoneNumber isn't valid");
                return "edit-patient";
            }
        }
        Patient patient = convertPatientDTOToPatient(patientDTO);
        patientService.updatePatientRecord(id, patient);
        logger.info("Request processed successfully for updating patient record | patientId: {}", id);
        return "redirect:/";
    }

    private Patient convertPatientDTOToPatient(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }

    private PatientDTO convertPatientToPatientDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }
}
