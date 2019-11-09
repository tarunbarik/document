package org.document.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.document.entities.Document;
import org.document.repos.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class DocumentController {
	@Autowired
	private DocumentRepository documentRepository;

	/***
	 * This method is for display the document upload page.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/displayUpload")
	public String displayUpload(ModelMap modelMap) {
		List<Document> documents = documentRepository.findAll();
		System.out.println(documents.size());
		modelMap.addAttribute("documents", documents);
		return "documentUpload";
	}// displayUpload()

	/***
	 * This method is used for process the upload document
	 * 
	 * @param multipart
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String documentUpload(@RequestParam("document") MultipartFile multipart, @RequestParam("id") long id,
			ModelMap modelMap) {
		Document document = new Document();
		document.setId(id);
		// document.setName(multipart.getName());
		document.setName(multipart.getOriginalFilename());
		try {
			document.setData(multipart.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		documentRepository.save(document);

		List<Document> documents = documentRepository.findAll();
		System.out.println(documents.size());
		modelMap.addAttribute("documents", documents);
		return "documentUpload";
	}// documentUpload()

	/***
	 * This method is used for download the document.
	 * 
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/download")
	public StreamingResponseBody download(@RequestParam("id") long id, HttpServletResponse response) {
		Document document = documentRepository.findById(id).get();
		byte[] data = document.getData();

		response.setHeader("Content-Disposition", "attachment;filename:downloaded.jpge");
		return outputStream -> {
			outputStream.write(data);
		};
	}// download()
}// class