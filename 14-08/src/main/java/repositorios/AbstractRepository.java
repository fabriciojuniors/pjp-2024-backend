package repositorios;

import configuracao.Conexao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractRepository<T> {

    private Class<T> clazz;

//    @PostConstruct
//    public void init() {
//        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
//        this.clazz = ((Class) type.getActualTypeArguments()[0]);
//    }

    public AbstractRepository() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = ((Class) type.getActualTypeArguments()[0]);
    }

    public List<T> findAll() {
        return Conexao.getEntityManager()
                .createQuery("select c from " + clazz.getSimpleName() + " c", clazz)
                .getResultList();
    }

}
