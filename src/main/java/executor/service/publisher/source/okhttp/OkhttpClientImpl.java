package executor.service.publisher.source.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import executor.service.publisher.exception.source.DataParsingException;
import executor.service.publisher.exception.source.okhttp.CallException;
import executor.service.publisher.exception.source.okhttp.EmptyResponseBodyException;
import executor.service.publisher.exception.source.okhttp.UnsuccessfulResponseException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class OkhttpClientImpl implements OkhttpClient {
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public OkhttpClientImpl(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> List<T> loadData(Request request, Class<T> clazz) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) return handleSuccessfulResponse(clazz, response);
            throw new UnsuccessfulResponseException(response.code());
        } catch (IOException ex) {
            throw new CallException(ex);
        }
    }

    private <T> List<T> handleSuccessfulResponse(Class<T> clazz, Response response) {
        try(ResponseBody body = response.body()){
            if(body == null) throw new EmptyResponseBodyException();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(body.string(), collectionType);
        } catch (IOException ex) {
            throw new DataParsingException(ex);
        }
    }

}