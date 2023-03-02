package com.demo.myshoeapp.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsersDTO {
    private List<UserDTO> userDTOList;
    private Integer currentPage;
    private long totalItems;
    private Integer totalPages;
}
