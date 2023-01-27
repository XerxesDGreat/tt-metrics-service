
/*
* (c) Copyright IBM Corporation 2021
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.asyncapi.models;
  
import com.asyncapi.models.ModelContract;
import java.util.UUID;
public class MetricsEventMessage  extends ModelContract{
  public int timestamp;
  public String eventName;
  public String value;
  public MetricsEventMessage(int timestamp,String eventName,String value) {
    
    this.timestamp = timestamp;
    
    this.eventName = eventName;
    
    this.value = value;
    
  }
  public MetricsEventMessage() {
    super();
  }
}
