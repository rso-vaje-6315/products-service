package si.rso.products.api;

import com.kumuluz.ee.discovery.annotations.RegisterService;
import com.kumuluz.ee.graphql.GraphQLApplication;
import com.kumuluz.ee.graphql.annotations.GraphQLApplicationClass;
import si.rso.products.api.config.AuthRole;

import javax.annotation.security.DeclareRoles;

@RegisterService
@GraphQLApplicationClass
@DeclareRoles({AuthRole.SERVICE, AuthRole.ADMIN, AuthRole.SELLER, AuthRole.CUSTOMER})
public class GraphQLService extends GraphQLApplication {
}
