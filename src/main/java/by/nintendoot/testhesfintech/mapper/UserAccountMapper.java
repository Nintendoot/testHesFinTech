package by.nintendoot.testhesfintech.mapper;

import by.nintendoot.testhesfintech.entity.UserAccount;
import by.nintendoot.testhesfintech.entity.UserAccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserAccountMapper {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserAccountMapper(ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccount toEntity(final UserAccountDTO userAccountDTO) {
        return Objects.isNull(userAccountDTO) ? null : modelMapper.map(userAccountDTO, UserAccount.class);
    }

    public UserAccountDTO toDto(final UserAccount userAccount) {
        return Objects.isNull(userAccount) ? null : modelMapper.map(userAccount, UserAccountDTO.class);
    }
}
