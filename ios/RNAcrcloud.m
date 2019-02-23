
#import "RNAcrcloud.h"
#import "ACRCloudRecognition.h"
#import "ACRCloudConfig.h"
#import <React/RCTConvert.h>

@implementation RNAcrcloud
{
    ACRCloudRecognition     *_client;
    ACRCloudConfig          *_config;
    UITextView              *_resultTextView;
    NSTimeInterval          startTime;
    __block BOOL    _start;
    RCTResponseSenderBlock _resolve;
    RCTResponseSenderBlock _reject;
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE(Acrcloud)

RCT_EXPORT_METHOD(initACRCloud:(NSDictionary *)params
                  errorCallback:(RCTResponseSenderBlock)errorCallback)
{
    NSString *host = [RCTConvert NSString:params[@"host"]];
    NSString *accessKey = [RCTConvert NSString:params[@"accessKey"]];
    NSString *accessSecret = [RCTConvert NSString:params[@"accessSecret"]];
    
    _start = NO;
    
    _config = [[ACRCloudConfig alloc] init];
    
    _config.accessKey = accessKey;
    _config.accessSecret = accessSecret;
    _config.host = host;
    _config.protocol = @"https";
    
    _config.recMode = rec_mode_remote;
    _config.requestTimeout = 10;
    
    __weak typeof(self) weakSelf = self;
    
    _config.resultBlock = ^(NSString *result, ACRCloudResultType resType) {
        [weakSelf handleResult:result resultType:resType];
    };
    
    _client = [[ACRCloudRecognition alloc] initWithConfig:_config];
    
    //start pre-record
    [_client startPreRecord:3000];
}

RCT_EXPORT_METHOD(startRecognition:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    if (_start) {
        return;
    }
    
    [_client startRecordRec];
    _start = YES;
    
    startTime = [[NSDate date] timeIntervalSince1970];
    _resolve = resolve;
}

RCT_EXPORT_METHOD(stopRecognition)
{
    if(_client) {
        [_client stopRecordRec];
    }
    _start = NO;
}

-(void)handleResult:(NSString *)result
         resultType:(ACRCloudResultType)resType
{
    [_client stopRecordRec];
    _start = NO;
    
    // convert json string to NSArray
    NSData* data = [result dataUsingEncoding:NSUTF8StringEncoding];
    NSArray *values = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:nil];
    
    _resolve(@[values]);
}

@end
  
