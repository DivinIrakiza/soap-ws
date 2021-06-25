package com.rca.spring.exam.soapws.endpoints;


import com.rca.spring.exam.soapws.domains.SupplierModel;
import com.rca.spring.exam.soapws.repositories.ISupplierRepository;
import exam.spring.rca.com.divinirakiza.soapws.GetAllSuppliersRequest;
import exam.spring.rca.com.divinirakiza.soapws.GetAllSuppliersResponse;
import exam.spring.rca.com.divinirakiza.soapws.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class SupplierEndPoint {
    private final ISupplierRepository supplierRepository;

    @Autowired
    public SupplierEndPoint(ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "GetAllStudentsRequest")
    @ResponsePayload
    public GetAllSuppliersResponse getAll(@RequestPayload GetAllSuppliersRequest request){

        List<SupplierModel> entities = this.supplierRepository.findAll();

        GetAllSuppliersResponse response = new GetAllSuppliersResponse();

        for (SupplierModel supplierModel: entities){
            Supplier supplier = new Supplier();
            supplier.setId(supplierModel.getId());
            supplier.setEmail(supplierModel.getEmail());
            supplier.setNames(supplierModel.getNames());
            supplier.setMobile(supplierModel.getMobile());

            response.getSupplier().add(supplier);
        }

        return response;
    }

    @PayloadRoot(namespace = "https://rca.ac.rw/verie/soap-app/student", localPart = "GetStudentDetailsRequest")
    @ResponsePayload
    public GetStudentDetailsResponse findById(@RequestPayload GetStudentDetailsRequest request){
        Optional<Student> _student = studentRepository.findById(request.getId());

        if(!_student.isPresent())
            return new GetStudentDetailsResponse();

        Student student = _student.get();

        GetStudentDetailsResponse response = new GetStudentDetailsResponse();

        https.rca_ac_rw.verie.soap_app.student.Student __student = new https.rca_ac_rw.verie.soap_app.student.Student();
        __student.setId(student.getId());
        __student.setFirstName(student.getFirstName());
        __student.setLastName(student.getLastName());
        __student.setGender(student.getGender());


        response.setStudent(__student);

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


    @PayloadRoot(namespace = "https://rca.ac.rw/verie/soap-app/student", localPart = "DeleteStudentRequest")
    @ResponsePayload
    public DeleteStudentResponse delete(@RequestPayload DeleteStudentRequest request){
        studentRepository.deleteById(request.getId());
        DeleteStudentResponse response = new DeleteStudentResponse();
        response.setMessage("Successfully deleted a message");
        return response;
    }

    @PayloadRoot(namespace = "https://rca.ac.rw/verie/soap-app/student", localPart = "UpdateStudentRequest")
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