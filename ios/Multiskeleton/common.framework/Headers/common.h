#import <Foundation/Foundation.h>

@class CommonBasePresenter, CommonPresenterConfiguration, CommonStdlibThrowable, CommonResponse, CommonResponseBody, CommonIpModel, CommonExample1Presenter, CommonExample2Presenter;

@protocol CommonBaseContract, CommonBaseContractView, CommonBaseContractPresenter, CommonUserSettings, CommonRestApi, CommonLogger, CommonCrashReporter, CommonCall, CommonCallback, CommonExample1Contract, CommonExample1ContractView, CommonExample1ContractPresenter, CommonExample2Contract, CommonExample2ContractView, CommonExample2ContractPresenter;

NS_ASSUME_NONNULL_BEGIN

@interface KotlinBase : NSObject
-(instancetype) init __attribute__((unavailable));
+(instancetype) new __attribute__((unavailable));
+(void)initialize __attribute__((objc_requires_super));
@end;

@interface KotlinBase (KotlinBaseCopying) <NSCopying>
@end;

__attribute__((objc_runtime_name("KotlinMutableSet")))
@interface CommonMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((objc_runtime_name("KotlinMutableDictionary")))
@interface CommonMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@protocol CommonBaseContract
@required
@end;

@protocol CommonBaseContractView
@required
@end;

@protocol CommonBaseContractPresenter
@required
-(void)bindViewView:(id<CommonBaseContractView>)view NS_SWIFT_NAME(bindView(view:));
-(void)unbindView NS_SWIFT_NAME(unbindView());
-(void)onViewCreated NS_SWIFT_NAME(onViewCreated());
-(void)onViewDestroyed NS_SWIFT_NAME(onViewDestroyed());
@end;

@interface CommonBasePresenter : KotlinBase <CommonBaseContractPresenter>
-(instancetype)initWithView:(id<CommonBaseContractView> _Nullable)view configuration:(CommonPresenterConfiguration*)configuration NS_SWIFT_NAME(init(view:configuration:)) NS_DESIGNATED_INITIALIZER;

-(void)onViewBound NS_SWIFT_NAME(onViewBound());
-(void)onViewUnbound NS_SWIFT_NAME(onViewUnbound());
@property (readonly) id<CommonUserSettings> userSettings;
@property (readonly) id<CommonRestApi> restApi;
@property (readonly) id<CommonLogger> logger;
@property (readonly) id<CommonCrashReporter> crashReporter;
@property id<CommonBaseContractView> _Nullable view;
@end;

@interface CommonPresenterConfiguration : KotlinBase
-(instancetype)initWithUserSettings:(id<CommonUserSettings>)userSettings restApi:(id<CommonRestApi>)restApi logger:(id<CommonLogger>)logger crashReporter:(id<CommonCrashReporter>)crashReporter NS_SWIFT_NAME(init(userSettings:restApi:logger:crashReporter:)) NS_DESIGNATED_INITIALIZER;

@property (readonly) id<CommonUserSettings> userSettings;
@property (readonly) id<CommonRestApi> restApi;
@property (readonly) id<CommonLogger> logger;
@property (readonly) id<CommonCrashReporter> crashReporter;
@end;

@protocol CommonRestApi
@required
-(id<CommonCall>)getMyIp NS_SWIFT_NAME(getMyIp());
@end;

@protocol CommonCall
@required
-(void)enqueueCallback:(id<CommonCallback>)callback NS_SWIFT_NAME(enqueue(callback:));
-(BOOL)isExecuted NS_SWIFT_NAME(isExecuted());
-(void)cancel NS_SWIFT_NAME(cancel());
-(BOOL)isCanceled NS_SWIFT_NAME(isCanceled());
@end;

@protocol CommonCallback
@required
-(void)onFailureCall:(id<CommonCall>)call t:(CommonStdlibThrowable*)t NS_SWIFT_NAME(onFailure(call:t:));
-(void)onResponseCall:(id<CommonCall>)call response:(CommonResponse*)response NS_SWIFT_NAME(onResponse(call:response:));
@end;

__attribute__((objc_subclassing_restricted))
@interface CommonResponse : KotlinBase
-(instancetype)initWith_body:(id _Nullable)_body _errorBody:(CommonResponseBody* _Nullable)_errorBody _isSuccessful:(BOOL)_isSuccessful NS_SWIFT_NAME(init(_body:_errorBody:_isSuccessful:)) NS_DESIGNATED_INITIALIZER;

-(BOOL)isSuccessful NS_SWIFT_NAME(isSuccessful());
-(id _Nullable)body NS_SWIFT_NAME(body());
-(CommonResponseBody* _Nullable)errorBody NS_SWIFT_NAME(errorBody());
@end;

@interface CommonResponseBody : KotlinBase
-(instancetype)initWith_raw:(NSString*)_raw NS_SWIFT_NAME(init(_raw:)) NS_DESIGNATED_INITIALIZER;

@end;

@protocol CommonUserSettings
@required
-(NSString*)getLastIp NS_SWIFT_NAME(getLastIp());
-(void)setLastIpIp:(NSString*)ip NS_SWIFT_NAME(setLastIp(ip:));
@end;

__attribute__((objc_subclassing_restricted))
@interface CommonIpModel : KotlinBase
-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER;

@property NSString* ip;
@end;

@protocol CommonExample1Contract
@required
@end;

@protocol CommonExample1ContractView <CommonBaseContractView>
@required
-(void)gotoExample2 NS_SWIFT_NAME(gotoExample2());
@end;

@protocol CommonExample1ContractPresenter <CommonBaseContractPresenter>
@required
-(void)onButtonClick NS_SWIFT_NAME(onButtonClick());
@end;

__attribute__((objc_subclassing_restricted))
@interface CommonExample1Presenter : CommonBasePresenter <CommonExample1ContractPresenter>
-(instancetype)initWithView:(id<CommonExample1ContractView>)view configuration:(CommonPresenterConfiguration*)configuration NS_SWIFT_NAME(init(view:configuration:)) NS_DESIGNATED_INITIALIZER;

@property id<CommonExample1ContractView> _Nullable view;
@end;

@protocol CommonExample2Contract
@required
@end;

@protocol CommonExample2ContractView <CommonBaseContractView>
@required
-(void)showCurrentIpAddressIp:(NSString*)ip NS_SWIFT_NAME(showCurrentIpAddress(ip:));
-(void)showPreviousIpAddressIp:(NSString*)ip NS_SWIFT_NAME(showPreviousIpAddress(ip:));
-(void)hidePreviousIpAddress NS_SWIFT_NAME(hidePreviousIpAddress());
@end;

@protocol CommonExample2ContractPresenter <CommonBaseContractPresenter>
@required
@end;

__attribute__((objc_subclassing_restricted))
@interface CommonExample2Presenter : CommonBasePresenter <CommonExample2ContractPresenter>
-(instancetype)initWithView:(id<CommonExample2ContractView>)view configuration:(CommonPresenterConfiguration*)configuration NS_SWIFT_NAME(init(view:configuration:)) NS_DESIGNATED_INITIALIZER;

@property id<CommonExample2ContractView> _Nullable view;
@end;

@protocol CommonCrashReporter
@required
-(void)logPriority:(int32_t)priority tag:(NSString* _Nullable)tag message:(NSString*)message NS_SWIFT_NAME(log(priority:tag:message:));
-(void)logExceptionThrowable:(CommonStdlibThrowable*)throwable NS_SWIFT_NAME(logException(throwable:));
@end;

@protocol CommonLogger
@required
-(void)vMessage:(NSString*)message NS_SWIFT_NAME(v(message:));
-(void)vT:(CommonStdlibThrowable*)t message:(NSString*)message NS_SWIFT_NAME(v(t:message:));
-(void)vT:(CommonStdlibThrowable*)t NS_SWIFT_NAME(v(t:));
-(void)dMessage:(NSString*)message NS_SWIFT_NAME(d(message:));
-(void)dT:(CommonStdlibThrowable*)t message:(NSString*)message NS_SWIFT_NAME(d(t:message:));
-(void)dT:(CommonStdlibThrowable*)t NS_SWIFT_NAME(d(t:));
-(void)iMessage:(NSString*)message NS_SWIFT_NAME(i(message:));
-(void)iT:(CommonStdlibThrowable*)t message:(NSString*)message NS_SWIFT_NAME(i(t:message:));
-(void)iT:(CommonStdlibThrowable*)t NS_SWIFT_NAME(i(t:));
-(void)wMessage:(NSString*)message NS_SWIFT_NAME(w(message:));
-(void)wT:(CommonStdlibThrowable*)t message:(NSString*)message NS_SWIFT_NAME(w(t:message:));
-(void)wT:(CommonStdlibThrowable*)t NS_SWIFT_NAME(w(t:));
-(void)eMessage:(NSString*)message NS_SWIFT_NAME(e(message:));
-(void)eT:(CommonStdlibThrowable*)t message:(NSString*)message NS_SWIFT_NAME(e(t:message:));
-(void)eT:(CommonStdlibThrowable*)t NS_SWIFT_NAME(e(t:));
-(void)wtfMessage:(NSString*)message NS_SWIFT_NAME(wtf(message:));
-(void)wtfT:(CommonStdlibThrowable*)t message:(NSString*)message NS_SWIFT_NAME(wtf(t:message:));
-(void)wtfT:(CommonStdlibThrowable*)t NS_SWIFT_NAME(wtf(t:));
@end;

@interface CommonStdlibThrowable : KotlinBase
-(instancetype)initWithMessage:(NSString* _Nullable)message NS_SWIFT_NAME(init(message:)) NS_DESIGNATED_INITIALIZER;

-(instancetype)initWithCause:(CommonStdlibThrowable* _Nullable)cause NS_SWIFT_NAME(init(cause:)) NS_DESIGNATED_INITIALIZER;

-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER;

-(instancetype)initWithMessage:(NSString* _Nullable)message cause:(CommonStdlibThrowable* _Nullable)cause NS_SWIFT_NAME(init(message:cause:)) NS_DESIGNATED_INITIALIZER;

-(void)printStackTrace NS_SWIFT_NAME(printStackTrace());
@property (readonly) CommonStdlibThrowable* _Nullable cause;
@property (readonly) NSString* _Nullable message;
@end;

NS_ASSUME_NONNULL_END
