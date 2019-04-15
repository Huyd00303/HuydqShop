package com.huydq.demo.controller;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.huydq.demo.dao.ProductDao;
import com.huydq.demo.model.Products;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	 private static String UPLOADED_FOLDER = "D:/Code Java/HuydqShop/src/main/resources/static/assets/img/";
	
	@Autowired
	ProductDao productDao;
	

	@GetMapping(value = "/read")
	public List<Products> read() {
		List<Products> list = productDao.getAllProduct();
		return list;
	}

	@PostMapping(value = "/create")
	public List<Products> create(@RequestBody Products product) {
		productDao.addProduct(product);
		List<Products> list = productDao.getAllProduct();
		return list;
	}

	@PutMapping(value = "/update")
	public List<Products> update(@RequestBody Products product) {
		productDao.updateProduct(product);
		List<Products> list = productDao.getAllProduct();
		return list;
	}

	@DeleteMapping(value = "/{id}")
	public List<Products> delete(@PathVariable("id") String id) {
		productDao.deleteProduct(id);
		List<Products> list = productDao.getAllProduct();
		return list;
		
	}
	
	@PostMapping("/upload") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
	// Them san pham
}
