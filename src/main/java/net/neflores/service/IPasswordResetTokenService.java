package net.neflores.service;


import net.neflores.util.PasswordResetToken;

public interface IPasswordResetTokenService {

	 PasswordResetToken findByToken(String token);

	void delete(PasswordResetToken token);
}
