.class Lcom/chartboost/sdk/impl/af$p;
.super Lcom/chartboost/sdk/impl/ac;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/chartboost/sdk/impl/af;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "p"
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    .prologue
    .line 373
    invoke-direct {p0}, Lcom/chartboost/sdk/impl/ac;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/chartboost/sdk/impl/af$p;)V
    .locals 0
    .parameter

    .prologue
    .line 373
    invoke-direct {p0}, Lcom/chartboost/sdk/impl/af$p;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Ljava/lang/Object;Ljava/lang/StringBuilder;)V
    .locals 0
    .parameter
    .parameter

    .prologue
    .line 377
    check-cast p1, Ljava/lang/String;

    invoke-static {p2, p1}, Lcom/chartboost/sdk/impl/ae;->a(Ljava/lang/StringBuilder;Ljava/lang/String;)V

    .line 378
    return-void
.end method
