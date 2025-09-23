package com.eraasoft.spring.controller;

import com.eraasoft.spring.service.DTO.EraaSoftSchoolDTO;
import com.eraasoft.spring.service.EraaSoftShoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Tag(
//        name = "Product Controller",
//        description = "apis manage Products"
//)
@RestController
@RequestMapping("/eraa-soft")
//@Controller
//@CrossOrigin("link ang")
public class EraaSoftSchoolController {
    private EraaSoftShoolService eraaSoftShoolService;
//String save(EraaSoftSchool eraaSoftSchool){
//    return  "/student/successSave";
//}
    @Autowired
    public EraaSoftSchoolController(EraaSoftShoolService eraaSoftShoolService) {
        this.eraaSoftShoolService = eraaSoftShoolService;
    }
    @PostMapping("/save-student")
//    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<EraaSoftSchoolDTO> addStudent(@RequestBody @Valid EraaSoftSchoolDTO eraaSoftSchoolDTO){return ResponseEntity.ok(eraaSoftShoolService.save(eraaSoftSchoolDTO));}
    @PutMapping("/update-student")
//    @Operation(summary = "",description = "")
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Http Status get all products",
//                    content = @Content(
//                            schema = @Schema(implementation = ProductResponseVm.class)
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "Http Status internal server error",
//                    content = @Content(
//                            schema = @Schema(implementation = ExceptionDto.class)
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "404",
//                    description = "Http Status Not Found",
//                    content = @Content(
//                            schema = @Schema(implementation = ExceptionDto.class)
//                    )
//            )
//    })
    ResponseEntity<EraaSoftSchoolDTO> updateStudent(/*@Parameter(description = "this page contain products and must start with 1")*/ @RequestBody EraaSoftSchoolDTO eraaSoftSchoolDTO){return ResponseEntity.ok(eraaSoftShoolService.update(eraaSoftSchoolDTO));}
    @DeleteMapping("/delete-student")/*("/delete-student/{id}")*/
    boolean deleteStudent(@RequestParam/*("newName") or @PathVariable*/ Long id){return eraaSoftShoolService.delete(id);}
    @GetMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<EraaSoftSchoolDTO>> getAllStudent(){return ResponseEntity.ok(eraaSoftShoolService.getAll());}
    @GetMapping("/student/{id}")
    ResponseEntity<EraaSoftSchoolDTO> getStudentById(@PathVariable Long id){return ResponseEntity.ok(eraaSoftShoolService.getById(id));}
    @GetMapping("/student/user/name/{userName}")
    ResponseEntity<EraaSoftSchoolDTO> getStudentById(@PathVariable String userName){return ResponseEntity.ok(eraaSoftShoolService.getByUserName(userName));}
    @GetMapping("/student/user/name2/{name}")
    ResponseEntity<EraaSoftSchoolDTO> extByName(@PathVariable String name){return ResponseEntity.ok(eraaSoftShoolService.extByName(name));}

}
