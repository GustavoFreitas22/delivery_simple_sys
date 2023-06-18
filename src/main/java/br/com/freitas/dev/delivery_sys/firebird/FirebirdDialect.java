package br.com.freitas.dev.delivery_sys.firebird;

import org.springframework.data.relational.core.dialect.AnsiDialect;
import org.springframework.data.relational.core.dialect.ArrayColumns;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.LockOptions;

public class FirebirdDialect extends AnsiDialect {

    public static final FirebirdDialect INSTANCE = new FirebirdDialect();

    @Override
    public LockClause lock() {
        return LOCK_CLAUSE;
    }

    @Override
    public ArrayColumns getArraySupport() {
        return ArrayColumns.Unsupported.INSTANCE;
    }

    private static final LockClause LOCK_CLAUSE = new LockClause() {

        @Override
        public String getLock(LockOptions lockOptions) {
            return "WITH LOCK";
        }

        @Override
        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }
    };
}