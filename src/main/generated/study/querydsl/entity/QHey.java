package study.querydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHey is a Querydsl query type for Hey
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHey extends EntityPathBase<Hey> {

    private static final long serialVersionUID = -11420059L;

    public static final QHey hey = new QHey("hey");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QHey(String variable) {
        super(Hey.class, forVariable(variable));
    }

    public QHey(Path<? extends Hey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHey(PathMetadata metadata) {
        super(Hey.class, metadata);
    }

}

