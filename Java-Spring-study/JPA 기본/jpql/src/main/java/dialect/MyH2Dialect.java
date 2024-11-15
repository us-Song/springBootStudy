package dialect;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect implements FunctionContributor {
    //하이버네이트 6 부터는 사용자 정의 함수 등록이 많이 바꼇음.
    //https://hardlearner.tistory.com/389 참고
    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions.getFunctionRegistry()
                .registerNamed("group_concat", functionContributions
                        .getTypeConfiguration()
                        .getBasicTypeRegistry()
                        .resolve(StandardBasicTypes.STRING));
    }
}
