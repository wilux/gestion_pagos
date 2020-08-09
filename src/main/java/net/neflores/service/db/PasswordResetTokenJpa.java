package net.neflores.service.db;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import net.neflores.repository.PasswordResetTokenRepository;
import net.neflores.service.IPasswordResetTokenService;
import net.neflores.util.PasswordResetToken;


@Service
@Primary
public class PasswordResetTokenJpa implements IPasswordResetTokenService {

	@Autowired
	private PasswordResetTokenRepository tokenRepo;

	@Override
	public PasswordResetToken findByToken(String token) {
		// TODO Auto-generated method stub
		return tokenRepo.findByToken(token);
	}

	@Override
	public void delete(PasswordResetToken token) {
		tokenRepo.delete(token);
		
	}


	
}
