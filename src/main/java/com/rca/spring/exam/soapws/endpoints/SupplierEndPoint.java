package com.rca.spring.exam.soapws.endpoints;


import com.rca.spring.exam.soapws.domains.SupplierModel;
import com.rca.spring.exam.soapws.repositories.ISupplierRepository;
import exam.spring.rca.com.divinirakiza.soapws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class SupplierEndPoint {
    private final ISupplierRepository supplierRepository;

    @Autowired
    public SupplierEndPoint(ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "GetAllSuppliersRequest")
    @ResponsePayload
    public GetAllSuppliersResponse getAll(@RequestPayload GetAllSuppliersRequest request){

        List<SupplierModel> suppliers = this.supplierRepository.findAll();

        GetAllSuppliersResponse response = new GetAllSuppliersResponse();

        for (SupplierModel supplier: suppliers){
            Supplier _supplier = new Supplier();
            _supplier.setId(supplier.getId());
            _supplier.setEmail(supplier.getEmail());
            _supplier.setNames(supplier.getNames());
            _supplier.setMobile(supplier.getMobile());

            response.getSupplier().add(_supplier);
        }

        return response;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "GetSupplierRequest")
    @ResponsePayload
    public GetSupplierResponse getById(@RequestPayload GetSupplierRequest request){
        Optional<SupplierModel> supplier = this.supplierRepository.findById(request.getId());

        if(!supplier.isPresent())
            return new GetSupplierResponse();


        GetSupplierResponse response = new GetSupplierResponse();

        Supplier _supplier = new Supplier();
        _supplier.setId(supplier.get().getId());
        _supplier.setEmail(supplier.get().getEmail());
        _supplier.setNames(supplier.get().getNames());
        _supplier.setMobile(supplier.get().getMobile());

        response.setSupplier(_supplier);

        return response;
    }


    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "NewStudentDTORequest")
    @ResponsePayload
    public NewStudentDTOResponse create(@RequestPayload NewStudentDTORequest dto) {
        https.rca_ac_rw.verie.soap_app.student.Student __student = dto.getStudent();
        Student _student = new Student(__student.getFirstName(), __student.getFirstName(), __student.getGender());
        Student student = studentRepository.save(_student);
        NewStudentDTOResponse studentDTO = new NewStudentDTOResponse();
        __student.setId(student.getId());
        studentDTO.setStudent(__student);
        return studentDTO;
    }


    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "DeleteStudentRequest")
    @ResponsePayload
    public DeleteStudentResponse delete(@RequestPayload DeleteStudentRequest request){
        studentRepository.deleteById(request.getId());
        DeleteStudentResponse response = new DeleteStudentResponse();
        response.setMessage("Successfully deleted a message");
        return response;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "UpdateStudentRequest")
    @ResponsePayload
    public UpdateStudentResponse update(@RequestPayload UpdateStudentRequest request){
        https.rca_ac_rw.verie.soap_app.student.Student __student = request.getStudent();

        Student _student = new Student(__student.getFirstName(), __student.getFirstName(), __student.getGender());
        _student.setId(__student.getId());

        Student student = studentRepository.save(_student);

        UpdateStudentResponse studentDTO = new UpdateStudentResponse();

        __student.setId(student.getId());

        studentDTO.setStudent(__student);

        return studentDTO;
    }
}}