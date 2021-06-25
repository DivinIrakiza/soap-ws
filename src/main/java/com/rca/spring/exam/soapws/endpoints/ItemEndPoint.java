package com.rca.spring.exam.soapws.endpoints;


import com.rca.spring.exam.soapws.domains.ItemModel;
import com.rca.spring.exam.soapws.domains.SupplierModel;
import com.rca.spring.exam.soapws.enums.EItemStatus;
import com.rca.spring.exam.soapws.repositories.IItemRepository;
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
public class ItemEndPoint {
    private final ISupplierRepository supplierRepository;
    private final IItemRepository itemRepository;

    @Autowired
    public ItemEndPoint(IItemRepository itemRepository, ISupplierRepository supplierRepository) {
        this.itemRepository = itemRepository;
        this.supplierRepository = supplierRepository;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "GetAllItemsRequest")
    @ResponsePayload
    public GetAllItemsResponse getAll(@RequestPayload GetAllItemsRequest request){

        List<ItemModel> items = this.itemRepository.findAll();

        GetAllItemsResponse response = new GetAllItemsResponse();

        for (ItemModel item: items){
            Item _item = mapItem(item);
            response.getItem().add(_item);
        }

        return response;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "GetSupplierRequest")
    @ResponsePayload
    public GetItemResponse getById(@RequestPayload GetItemRequest request){
        Optional<ItemModel> item = this.itemRepository.findById(request.getId());

        if(!item.isPresent())
            return new GetItemResponse();


        GetItemResponse response = new GetItemResponse();

        Item _item = mapItem(item.get());

        response.setItem(_item);

        return response;
    }


    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "CreateSupplierRequest")
    @ResponsePayload
    public CreateItemResponse create(@RequestPayload CreateItemRequest dto) {
        ItemDTO itemDTO = dto.getItem();

        Optional<SupplierModel> supplier = this.supplierRepository.findById(itemDTO.getSupplier());

        if(!supplier.isPresent())
            return new CreateItemResponse();


        ItemModel item = new ItemModel();
        item.setName(itemDTO.getName());
        item.setItemCode(itemDTO.getItemCode());
        item.setPrice(itemDTO.getPrice());
        item.setSupplier(supplier.get());
        item.setStatus(EItemStatus.valueOf(itemDTO.getStatus().toString()));


        ItemModel entity = this.itemRepository.save(supplier);
        CreateSupplierResponse response = new CreateSupplierResponse();

        response.setSupplier(mapSupplier(entity));
        response.setSuccess(true);
        return response;
    }


    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "UpdateSupplierRequest")
    @ResponsePayload
    public UpdateSupplierResponse update(@RequestPayload UpdateSupplierRequest request){
        SupplierDTO supplierDTO = request.getSupplier();

        Optional<SupplierModel> supplier = this.supplierRepository.findById(request.getId());

        if(!supplier.isPresent())
            return new UpdateSupplierResponse();

        supplier.get().setNames(supplierDTO.getNames());
        supplier.get().setEmail(supplierDTO.getEmail());
        supplier.get().setMobile(supplierDTO.getMobile());

        SupplierModel entity = this.supplierRepository.save(supplier.get());
        UpdateSupplierResponse response = new UpdateSupplierResponse();

        response.setSupplier(mapSupplier(entity));
        response.setSuccess(true);

        return response;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "DeleteStudentRequest")
    @ResponsePayload
    public DeleteSupplierResponse delete(@RequestPayload DeleteSupplierRequest request){
        Optional<SupplierModel> supplier = this.supplierRepository.findById(request.getId());

        if(!supplier.isPresent())
            return new DeleteSupplierResponse();

        this.supplierRepository.deleteById(request.getId());
        DeleteSupplierResponse response = new DeleteSupplierResponse();
        response.setSuccess(true);
        return response;
    }



    private Item mapItem(ItemModel itemModel) {
        Item item = new Item();
        item.setId(itemModel.getId());
        item.setName(itemModel.getName());
        item.setItemCode(itemModel.getItemCode());
        item.setPrice(itemModel.getPrice());
        item.setSupplier(mapSupplier(itemModel.getSupplier()));
        item.setStatus(EStatus.valueOf(itemModel.getStatus().toString()));

        return item;
    }


    private Supplier mapSupplier(SupplierModel supplierModel) {
        Supplier supplier = new Supplier();
        supplier.setId(supplierModel.getId());
        supplier.setEmail(supplierModel.getEmail());
        supplier.setNames(supplierModel.getNames());
        supplier.setMobile(supplierModel.getMobile());

        return supplier;
    }

    private SupplierModel mapSupplierModel(Supplier supplier) {
        SupplierModel supplierModel = new SupplierModel();
        supplierModel.setId(supplier.getId());
        supplierModel.setEmail(supplier.getEmail());
        supplierModel.setNames(supplier.getNames());
        supplierModel.setMobile(supplier.getMobile());

        return supplierModel;
    }
}
