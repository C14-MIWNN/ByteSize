package nl.miwnn.se14.bytesize.service.mapper;

import nl.miwnn.se14.bytesize.dto.ByteSizeUserDTO;
import nl.miwnn.se14.bytesize.model.ByteSizeUser;

/**
 * @author Heron
 * Converts between Model classes and DTOs
 */
public class ByteSizeUserMapper {

    public static ByteSizeUser fromDTO(ByteSizeUserDTO dto) {
        ByteSizeUser user = new ByteSizeUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setUserAboutMe(dto.getUserAboutMe());

        return user;
    }
}