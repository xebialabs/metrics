package com.codahale.metrics;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HistogramTest {
    private final Reservoir reservoir = mock(Reservoir.class);
    private final Histogram histogram = new Histogram(reservoir);

    @Test
    public void updatesTheCountAndSumOnUpdates() throws Exception {
        assertThat(histogram.getCount())
                .isZero();
        assertThat(histogram.getSum())
                .isZero();

        histogram.update(1);
        histogram.update(5);

        assertThat(histogram.getCount())
                .isEqualTo(2);
        assertThat(histogram.getSum())
                .isEqualTo(6);
    }

    @Test
    public void returnsTheSnapshotFromTheReservoir() throws Exception {
        final Snapshot snapshot = mock(Snapshot.class);
        when(reservoir.getSnapshot()).thenReturn(snapshot);

        assertThat(histogram.getSnapshot())
                .isEqualTo(snapshot);
    }

    @Test
    public void updatesTheReservoir() throws Exception {
        histogram.update(1);

        verify(reservoir).update(1);
    }
}
