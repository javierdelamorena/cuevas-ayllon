package com.cuevasdeayllon.service;

import java.util.List;

import com.cuevasdeayllon.post.managment.PostDTO;

public interface PostManagementService {
	
	List<PostDTO> list();

    Boolean add(PostDTO post);

    Boolean edit(String id,PostDTO post);

    Boolean delete(String id);


}
