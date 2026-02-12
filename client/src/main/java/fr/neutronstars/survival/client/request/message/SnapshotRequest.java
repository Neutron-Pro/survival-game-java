package fr.neutronstars.survival.client.request.message;

import fr.neutronstars.survival.client.snapshot.Snapshot;
import fr.neutronstars.survival.core.request.Request;

public record SnapshotRequest(Snapshot snapshot) implements Request {}
