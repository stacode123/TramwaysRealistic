package purplecreate.tramways.config;

import java.util.*;

public class TrainConfig {
  private String announcer = null;
  private Map<TrainMessageType, List<String>> messages = null;

  public static TrainConfig getInitial() {
    TrainConfig trainConfig = new TrainConfig();

    trainConfig.announcer = trainConfig.getAnnouncer();
    trainConfig.messages = new HashMap<>();

    for (TrainMessageType type : TrainMessageType.class.getEnumConstants())
      trainConfig.messages.put(type, trainConfig.getMessages(type));

    return trainConfig;
  }

  public String getAnnouncer() {
    if (Objects.isNull(announcer))
      return "en-GB-SoniaNeural";
    return announcer;
  }

  public List<String> getMessages(TrainMessageType type) {
    if (Objects.isNull(messages))
      return List.of(type.defaultString);
    List<String> msgs = messages.getOrDefault(type, List.of());
    if (msgs.isEmpty())
      return List.of(type.defaultString);
    return msgs;
  }

  public String getRandomMessage(TrainMessageType type) {
    List<String> msgs = getMessages(type);
    return msgs.get(new Random().nextInt(msgs.size()));
  }
}
