package br.ifsc.edu.fln.logixpraias.model;

import br.ifsc.edu.fln.logixpraias.enums.EGraduacao;
import br.ifsc.edu.fln.logixpraias.enums.ESetor;
import br.ifsc.edu.fln.logixpraias.enums.EPerfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected String email;
    protected String telefone;
    protected String mtcl;
    @Enumerated(EnumType.STRING)
    protected EPerfil perfil;
    protected LocalDate dataCadastro = LocalDate.now();
    @Enumerated(EnumType.STRING)
    protected EGraduacao graduacao;
    @Enumerated(EnumType.STRING)
    protected ESetor setor;
    protected String senha;

    public Usuario(String email, String senha, EPerfil perfil) {
        this.email = email;
        this.perfil = perfil;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return graduacao + " " + nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.perfil == EPerfil.ADMINISTRADOR) {return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"), new SimpleGrantedAuthority("ROLE_USER"));}
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
