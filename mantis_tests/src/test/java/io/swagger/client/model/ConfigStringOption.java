/*
 * MantisBT REST API
 * For the sandbox to work, MantisBT must be hosted at the root folder of the host. For example: http://mantishost/ rather http://host/mantisbt.  If that is not the case, then create a host alias to map it as such or edit swagger.json to change basePath to include the mantisbt folder name.
 *
 * OpenAPI spec version: 1.1.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ConfigStringOption
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2024-05-20T12:09:12.624+05:00")
public class ConfigStringOption {
  @SerializedName("option")
  private String option = null;

  @SerializedName("value")
  private String value = null;

  public ConfigStringOption option(String option) {
    this.option = option;
    return this;
  }

   /**
   * Get option
   * @return option
  **/
  @ApiModelProperty(value = "")
  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }

  public ConfigStringOption value(String value) {
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @ApiModelProperty(value = "")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigStringOption configStringOption = (ConfigStringOption) o;
    return Objects.equals(this.option, configStringOption.option) &&
        Objects.equals(this.value, configStringOption.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(option, value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigStringOption {\n");
    
    sb.append("    option: ").append(toIndentedString(option)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

